pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
               echo 'This is a minimal pipeline.'
            }
            steps {
                sh 'mvn -Dcoolcanvasusername=user3 -Dcoolcanvaspassword=CoolCanvas19. -Dtest=*Cases test'
            }
        }
    }
}