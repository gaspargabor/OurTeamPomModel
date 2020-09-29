pipeline {
    agent any


    stages {
        stage('Build') {
            steps {
                echo 'Build phase: '
                sh 'mvn clean'
            }
        }
        stage('Test') {
            steps {
                echo 'Test phase: '
                sh 'mvn -Dcoolcanvasusername=user3 -Dcoolcanvaspassword=CoolCanvas19. -Dtest=*Cases test'
            }

        post {
            always {
                archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
                junit 'build/reports/**/*.xml'

                echo 'One way or another, I have finished'
                deleteDir() /* clean up our workspace */
            }
        }
    }
}