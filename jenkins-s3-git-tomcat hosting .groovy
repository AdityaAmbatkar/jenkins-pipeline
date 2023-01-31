pipeline {
    agent {
        label 'nodename'
    }
    stages {
        stage ('code-pull') {
            steps {
                git credentialsId: 'a9fba870-53e0-4ea2-8057-97472dcd4d40', url: 'https://github.com/Sameermalamkar/onlinebookstore.git'            }
        }
        stage ('code-build') {
            steps {
                sh 'sudo apt update'
                sh 'sudo apt install maven -y'
                sh 'mvn clean package'
            }
        }
        stage ('Artifactacts to s3') {
            steps {
                withAWS(credentials: 'd784badf-f343-45b9-841f-af51c87ed55d', region: 'us-east-1') {
                    sh 'sudo apt install awscli -y'
                    sh 'aws s3 ls'
                    sh 'aws s3 mb s3://{bucketname} --region ap-south-1'
                    sh 'aws s3 cp  /home/ubuntu/workspace/{jobname}/target/onlinebookstore.war s3://{bucketname}'
                }
            }
        }
        stage ("Dev-Deployment") {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'a9fba870-53e0-4ea2-8057-97472dcd4d40', keyFileVariable: 'keyvariablename', usernameVariable: 'usernamevariablename')]) {
            sh'''
                  ssh -i ${keyvariablename} -o StrictHostKeyChecking=no ubuntu@{slaveinstanceip}<<EOF
                  sudo yum update -y
                  sudo yum install awscli -y 
                  aws --version
                  aws configure set aws_access_key_id {access-key]
                  aws configure set aws_secret_access_key {secret-access-key}
                  aws s3 ls
                  aws s3 cp s3://{bucketname}/onlinebookstore.war .
                  sudo curl -O https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.71/bin/apache-tomcat-9.0.71.tar.gz
                  sudo tar -xvf apache-tomcat-9.0.71.tar.gz -C /opt/
                  sudo sh /opt/apache-tomcat-9.0.71/bin/shutdown.sh
                  sudo cp -rv onlinebookstore.war bookstore.war
                  sudo cp -rv bookstore.war /opt/apache-tomcat-9.0.71/webapps/
                  sudo sh /opt/apache-tomcat-9.0.71/bin/startup.sh
                  '''
                 
                }
            } 
        }
    }
}