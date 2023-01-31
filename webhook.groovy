pipeline{
    agent {
        label 'sameer'
    } 
    stages {
        stage('code-pull') {
            steps{
                git 'https://github.com/Sameermalamkar/onlinebookstore.git'
            }
        }
    
        stage('code-build') {
            steps{
                sh 'sudo apt-get install maven -y'
                sh 'mvn clean package'
            }
        }
    
        stage('code-deploy') {
            steps{
                deploy adapters: [tomcat9(credentialsId: '29b983e9-1eed-4b45-8cd6-521d428c8bfd', path: '', url: 'http://100.26.210.237:8080/')], contextPath: '/', war: '**/*.war'
            }
        }
    }
}