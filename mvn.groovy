pipeline {
    agent any
    stages {
        stage ('code pull') {
            steps {
                   git 'https://github.com/Sameermalamkar/onlinebookstore.git'           
                 }
        }
        stage ('clean') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage ('artifacts') {
            steps {
s3Upload consoleLogLevel: 'INFO', dontSetBuildResultOnFailure: false, dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: 's3-maven-jenkins', excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: false, noUploadOnFailure: true, selectedRegion: 'us-east-1', showDirectlyInBrowser: false, sourceFile: 'target/*.war', storageClass: 'STANDARD', uploadFromSlave: false, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'sameer', userMetadata: []            }
        }
    }  
}