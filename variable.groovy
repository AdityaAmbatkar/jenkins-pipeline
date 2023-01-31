pipeline {
    agent any
    environment ('setting custom variable'){
        Prod_ip = "192.168.0.1"
        Dev_ip = "192.168.0.2"
        UAT_ip = "192.168.0.3"
    }
    stages {
        stage('printing jenking suggested variable') {
            steps {
                echo "this is the $LOGNAME project"
                echo "name of the project is $JOB_NAME $BUILD_ID"
                echo "and the project is running in $WORKSPACE"
                echo "job run by user $USER"
            }
        }
        stage ('printing custom variable') {
            steps{
                echo "ip of prod server is = $Prod_ip"
                echo "ip of dev server is = $dev_ip"
                echo "ip of UAT server is = $UAT_ip"
            }
        }
        stage ('printing step variable') {
            environment {
                Var1='Declarative'
                Var2='groovy'
                Var3='VScode'
            }
            steps {
                 echo "this is $Var1 pipeline with $Var2 extention created on $Var3"
            }
        }
    }
}