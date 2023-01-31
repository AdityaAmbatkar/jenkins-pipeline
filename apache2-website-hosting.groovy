pipeline {
    agent {
        label 'node1'
    }
    stages {
        stage ('hosting') {
            steps {
                sh 'sudo apt update'
                sh 'sudo apt install unzip -y'
                sh 'sudo apt install apache2 -y'
                sh 'sudo wget https://www.free-css.com/assets/files/free-css-templates/download/page287/cycle.zip'
                sh 'sudo unzip cycle.zip'
                sh 'sudo rm -rf /var/www/html/index.html'
                sh 'sudo mv html/* /var/www/html/'
                sh 'sudo systemctl start apache2'
                sh 'sudo systemctl enable apache2'
            }
        }
    }
}