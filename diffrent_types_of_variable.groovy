pipeline {
    agent any
    environment {
        Prod_server_ip='192.168.22.1'
        Dev_server_ip='192.168.22.2'
        UAT_server_ip='192.168.22.3'
        
    }
    stages {
        stage ('printing default variable') {
            steps{
                echo "this is the $LOGNAME project"
                echo "name of the project is $JOB_NAME $BUILD_ID"
                echo "and the project is running in $WORKSPACE"
                echo "job run by user $USER"
            }
        }
        stage ('local variable') {
            steps {
                echo "ip of prod server is $Prod_server_ip"
                echo "ip of dev server is $dev_server_ip"
                echo "ip off uat server is $uat_server_ip"
            }
        }
        stage ('printing step veriable') {
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