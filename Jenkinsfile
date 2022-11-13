pipeline{
agent any

        stages{
            stage ('GIT CHECKOUT 1') {
    steps {

     git branch: 'yassinedarwezi', url: 'https://github.com/yassinedarwezi/DevOps.git' }}

        stage('Compile Maven Project'){
                                                                     steps{
                                                                        sh 'mvn  compile '
                                                                     }
                                                                 }
 stage(' JUnit 2 ') {
 steps {
script {
sh 'echo "Mock & JUnit"'
sh 'mvn test'

 }
 }
 post {
  always {
junit '**/target/surefire-reports/TEST-*.xml'
   }
  }
 }
    stage(' SonarQube 3 ')
                 {
        steps{
    sh  ''' mvn sonar:sonar \
          -Dsonar.projectKey=Devops \
          -Dsonar.host.url=http://192.168.58.133:9000 \
          -Dsonar.login=bae718e46eafc0e2ad1d8e155e80cce189dfa8bd '''
     }
       }


  stage('Build Maven Spring 4 '){
  steps{
 sh 'mvn  clean install '
   }
  }

  stage('NexuS 5 ')
 {
  steps{
        echo "nexus"
        sh 'mvn clean '
       sh ' mvn deploy -DskipTests'
         }
       }

 stage('Build docker image 6 '){

 steps{
 script{
 sh 'docker build -t yassinedarwezi/devsecops .'
 }
 }
 }


 stage('Docker login  7 ') {

 steps {
 sh 'echo "login Docker ...."'
sh 'docker login -u yassinedarwezi -p 21693703aze'
  }  }
 stage('Docker push 8 ') {
 steps {
 sh 'echo "Docker is pushing ...."'
sh 'docker push yassinedarwezi/devsecops'
 }  }
 stage('Docker compose 9 ') {
  steps {
   sh 'docker-compose up -d'
  }  } }
post {
 success {
	 mail to: "yassine.darwezi0esprit.tn",
	subject: "Pipeline Backend Success ",
 	body: "Welcome to DevOps project Backend : Success on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL}"
}
	failure {
          mail to: "yassine.darwezi@esprit.tn",
           subject: "Pipeline backend Failure",
           body: "Welcome to DevOps project Backend : Failure on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL} "
                    }
                    }
      }