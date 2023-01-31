pipeline {
    agent {
        label 'node'
    }
    stages {
        stage ('http install') {
            steps {
                sh 'sudo yum update -y'
                sh 'sudo yum install httpd -y'
            }
        }
        stage ('remove html folder') {
            steps {
                sh 'sudo rm -rf /var/www/html'
                sh 'sudo cd /var/www'
                sh 'ls'
                sh 'sudo rm -rf html'
                sh 'ls'
            }
        }
        stage ('download template and extract in html destination') {
            steps {
                sh 'ls'
                sh 'sudo wget https://www.free-css.com/assets/files/free-css-templates/download/page287/cycle.zip'
                sh 'ls'
                sh 'sudo unzip cycle.zip'
                sh 'ls'
                sh 'sudo rm -rf cycle.zip'
                sh 'ls'
            }
        }
        stage ('starting httpd') {
            steps {
                sh 'sudo systemctl start httpd'
                sh 'sudo systemctl enable httpd'
            }
        }
    }
}