pipeline {
    agent any
    stages {
        stage ('gitclone') {
            steps {
                git branch: 'main', credentialsId: '2024f2a8-2139-44f5-8b63-3d5176354b5a', url: 'git@github.com:Sameermalamkar/new.git'
            }
        }
         stage ('gitclone') {
            steps {
                sh 'ls -ltr'
            }
        }
    }
}