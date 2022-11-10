pipeline {
    agent any
	environment {
		DOCKERHUB_CREDENTIALS=credentials('Dockerhub')
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.33.10:8081"
        NEXUS_REPOSITORY = "springboot"
        NEXUS_CREDENTIAL_ID = "Nexus"       
	}
    tools {
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "M2_HOME =${M2_HOME}"
                '''
            }
        }
        stage('Checkout git') {
            steps {
                echo 'pulling';
                git branch: 'master',
                url: 'https://github.com/iheboueslatiesprit/5TWIN-devops.git'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQubeLocal') {
                    sh 'mvn sonar:sonar'
                }
            }
        }


        stage('Build') {
            steps {
                sh 'mvn install -DskipTests=true'
            }
        }

        stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'Dockerhub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh 'sudo docker login -u $USERNAME -p $PASSWORD'
                }
            }
        }

        stage('Docker build') {
            steps {
                sh 'sudo docker build -t springboot .'
            }
        }
        stage('Docker tag') {
            steps {
                sh 'sudo docker tag 3321747afcd0 iheb120/springboot:springboot'
            }
        }

        stage('Docker push') {
            steps {
                sh 'sudo docker push iheb120/springboot:springboot'
            }
        }



    }
}