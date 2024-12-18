pipeline{
    agent any
    stages {
        stage('#001 - Build Jar of Framework')
        { steps { bat "mvn clean package -DskipTests"} }

        stage('#002 - Build Docker Image')
        { steps {   bat "docker build -t=rajat725/dockerframework ."} }

        stage('#003 - Push Docker Image to Docker hub')
        {   environment{ Docker_HUB = credentials('dockerhub-creds')}
		steps {
		 bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
		 bat "docker push rajat725/dockerframework"} }

         stage('#004 - Starting Grid'){ steps{bat "docker-compose -f grid.yaml up -d"}}

         stage('#005 - Executing Test Cases'){steps{bat "docker-compose -f tests.yaml up --pull=always"}}

    }

	post{

	 always {
        bat "docker logout"
        bat "docker-compose -f grid.yaml down"
        bat "docker-compose -f tests.yaml down"


        }


	}
}