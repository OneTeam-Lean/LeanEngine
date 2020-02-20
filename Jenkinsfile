pipeline {
   agent any

   stages {
      stage('Build') {
         steps {
            git 'https://github.com/OneTeam-Lean/LeanEngine.git'

            sh "./gradlew clean build -x test"
         }

         post {
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            success {
               echo "- done -"
            }
         }
      }
   }
}
