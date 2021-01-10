node("linux") {
    customImage = ""
    stage("Clone repo"){
        checkout scm
    }
    stage("Build image") {
        customImage = docker.build("dvdrbn/opsschool-jenkins-test-01")
    }
    stage("Test container") {
        docker.image(customImage).withRun{
            bash -c 'while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' localhost:80)" != "200" ]]; do sleep 5; done'
        }
    }
}
