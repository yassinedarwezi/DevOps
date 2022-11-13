pipeline{
agent any

        stages{
            stage ('SCM CHECKOUT') {
    steps {

     git branch: 'yassinedarwezi', url: 'https://github.com/yassinedarwezi/DevOps.git' }}

        stage('Compile Maven Project'){
                                                                     steps{
                                                                        sh 'mvn  compile '
                                                                     }
                                                                 }
 stage('Mock & JUnit') {
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
    stage('MVN SONARQUBE ')
                 {
        steps{
    sh  ''' mvn sonar:sonar \
          -Dsonar.projectKey=Devops \
          -Dsonar.host.url=http://192.168.58.132:9000 \
          -Dsonar.login=bae718e46eafc0e2ad1d8e155e80cce189dfa8bd '''
     }
       }


  stage('Build Maven Spring'){
  steps{
 sh 'mvn  clean install '
   }
  }

  stage('NEXUS')
 {
  steps{
        echo "nexus"
        sh 'mvn clean '
       sh ' mvn deploy -DskipTests'
         }
       }

 //stage('Build docker image'){

 steps{
 script{
 sh 'docker build -t yassinedarwezi/devsecops .'
 }
 }
 }


 stage('Docker login') {

 steps {
 sh 'echo "login Docker ...."'
sh 'docker login -u yassinedarwezi -p 21693703aze'
  }  }
 stage('Docker push') {
 steps {
 sh 'echo "Docker is pushing ...."'
sh 'docker push yassinedarwezi/devsecops'
 }  }
 stage('Docker compose') {
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