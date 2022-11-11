pipeline {
    agent any
  environment {

        registry = "hichembenzammel/tpachat"

        registryCredential = 'dockerhub'

        dockerImage = ''

    }


    stages {
        stage('Checkout Git') {
            steps {
                echo 'Pulling ...';
                git branch : 'ghazichhida',
                // Get some code from a GitHub repository
                url: 'https://github.com/yassinedarwezi/DevOps.git'

                // Get System Current Date

//                 mail body: 'Pipeline has been executed successfully', to: "@esprit.tn", subject: 'pipeline executed'
            }
        }


        stage("Maven Build") {
            steps {
                script {
                    sh "mvn clean install -DskipTests"
                }
            }
        }

        stage('MVN COMPILE') {
            steps {
               sh' mvn compile'

            }
         }

         stage('MVN SONARQUBE') {
                     steps {
                         sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=esprit'
                     }
                 }
//
//                  stage ('NEXUS DEPLOY') {
//                      steps {
//                          sh 'mvn clean package deploy:deploy-file -DgroupId=com.esprit.examen -DartifactId=tpAchatProject -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://192.168.43.231:8081/repository/maven-releases/ -Dfile=target/tpAchatProject-1.0.jar -DskipTests'
//                      }
//                  }
//
//                     stage('Building our image') {
//                  			steps {
//                  				script {
//                  					dockerImage = docker.build registry + ":$BUILD_NUMBER"
//                  					}
//                  				}
//                  		}
//                  		stage('Deploy our image') {
//                           steps {
//                           script {
//                               docker.withRegistry( '', registryCredential ) {
//                               dockerImage.push()
//                                 }
//                              }
//                            }
//
//                          }

/* stage       ('DOCKER COMPOSE') {
             steps {
                sh 'docker-compose up -d '
            }
        } */

//          stage("Unit tests") {
//            steps {
//                  sh "mvn test"
//            }
//         }


    }

}






