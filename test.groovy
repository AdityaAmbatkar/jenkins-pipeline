pipeline {
    agent {
        label 'slave'
    }
    stages {
        stage('info') {
            steps {
                sh'sudo yum install httpd -y'
                sh 'cd /var/www'
                sh 'sudo wget https://www.free-css.com/assets/files/free-css-templates/download/page287/beautiflie.zip'
                sh 'sudo unzip beautiflie.zip'
                sh 'sudo rm -rf beautiflie.zip'
                sh 'sudo systemctl start httpd'
                sh 'sudo systemctl enable httpd'
            }
        }
    }
}






pipeline {
    agent {
        label 'nikhil'
    }
    stages {
        stage ('hosting') {
            steps {
                sh 'sudo yum update -y'
                sh 'sudo yum install httpd -y'
                sh 'sudo chown root:root /home'
                sh 'sudo chmod 755 /home'
                sh 'sudo chown ec2-user:ec2-user /home/ec2-user -R'
                sh 'sudo chmod 777 /home/ec2-user /home/ec2-user/.ssh'
                sh 'sudo chmod 666 /home/ec2-user/.ssh/authorized_keys'
                sh 'sudo chown ec2-user:ec2-user /var/ -R'
                sh 'sudo echo "hello world" >> /var/www/html/index.html'
                sh 'sudo systemctl start httpd'
                sh 'sudo systemctl enable httpd'
                sh 'sudo wget https://www.free-css.com/assets/files/free-css-templates/download/page287/cycle.zip'
                sh 'sudo unzip -f cycle.zip'
                sh 'sudo mv cycle/* /var/www/html'
                sh 'sudo rm -rf /var/www/html/index.html'
                sh 'sudo systemctl restart httpd' 
            }
        }
    }
}