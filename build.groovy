node("linux") {
    imageName = "dvdrbn/opsschool-jenkins-test-01"
    customImage = ""
    stage("Clone repo"){
        checkout scm
    }
    stage("Build image") {
        customImage = docker.build(imageName)
    }
    stage("Test container") {
        docker.image(imageName).withRun{
            timeout(time: 15, unit: 'SECONDS') {
                waitUntil{
                    try {         
                        sh "curl -s --head  --request GET  localhost:80 | grep '200'"
                        return true
                    } catch (Exception e) {
                        return false
                  }
                }
            }
        }
    }
}
