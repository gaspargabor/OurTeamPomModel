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
        post {
            always {
                archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
                junit 'build/reports/**/*.xml'
            }
            always {
                echo 'One way or another, I have finished'
                deleteDir() /* clean up our workspace */
            }
        }
    }