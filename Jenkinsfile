pipeline {
  agent any
    stages {
      stage("stop docker"){
        steps {
	  sh "docker-compose down --rmi all"
	  sh "docker system prune -f"
	}
      }
      stage("mvn clean install"){
        steps {
	  sh "mvn clean install -Dmaven.test.skip=true"
	}
      }
      stage ("mvn package"){
        steps {
	  sh "mvn package -Dmaven.test.skip=true"
        }
      }
      stage ("docker up"){
        steps {
	  sh "docker-compose up -d"
	}
      }
    }
}
