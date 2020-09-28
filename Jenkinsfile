pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
               echo 'This is a minimal pipeline.'
                sh 'mvn clean'
            }
        }
        stage('Test') {
            steps {
                echo 'testing'
               sh 'mvn -Dcoolcanvasusername=user3 -Dcoolcanvaspassword=CoolCanvas19. -Dtest=*Cases test'
                }
            }
    }
}