pipeline {
   agent any

   stages {
      stage('Build') {
         steps {
            git 'https://github.com/OneTeam-Lean/LeanEngine.git'

            sh "./gradlew clean build"
         }

         post {
             always{
               junit 'build/test-results/**/*.xml'
               publishHTML (target: [
                             allowMissing: false,
                             alwaysLinkToLastBuild: false,
                             keepAll: true,
                             reportDir: 'build/reports/tests/test',
                             reportFiles: 'index.html',
                             reportName: "Tests"
               ])
             }
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            success {
               echo "- done -"
            }
         }
      }
   }
}
