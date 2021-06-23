// TODO change this to master as soon as the changes are merged
HIVEMQ_BASE_BRANCH= 'branch=create-composite-ci'

pipeline {
    // by default run on the master node, since it's the only one with direct repo access
    agent { label 'master' }
    options {
        // check out only in the Checkout stage, use stash afterwards
        skipDefaultCheckout()
        buildDiscarder(logRotator(numToKeepStr: '100'))
        // no concurrent builds so we don't run into problems generating the test splits, and to reduce load on the workers
        disableConcurrentBuilds()
        timestamps()
    }
    stages {
        stage('Update commit id in the composite repo') {
            steps {
                script {
                       commitToHiveMQCompositeRepo()
                }
            }
        }
        stage('Abort build') {
          steps {
            script {
                 println 'Aborting script to inhibit status setting in github'
                 currentBuild.result = 'ABORTED'
            }
          }
        }
    }
    post {
        cleanup {
            cleanWs()
        }
    }
}

private void commitToHiveMQCompositeRepo() {
    def projectName =  determineRepoName()
    def branchName =  env.BRANCH_NAME
    def repositoryUrl = determineRepoUrl()
    echo "repo url: ${repositoryUrl}"
    echo "branch: ${branchName}"

    def commitId = getLastCommitId(repositoryUrl, branchName)
    println "Try to commit the commit id to the composite repository"
    dir("tmp"){
      println "Git commit id: "+ commitId
      if(remoteBranchExists("git@github.com:hivemq/hivemq.git", branchName)){
         sh("git clone git@github.com:hivemq/hivemq.git --branch=" + branchName)
      }else{
         sh("git clone git@github.com:hivemq/hivemq.git --" + HIVEMQ_BASE_BRANCH)
         dir("hivemq/"){
             sh("git checkout -b " + branchName)
         }
      }
    }
    dir("tmp/hivemq/"){
        sh("git checkout " + branchName)
        sh("mkdir -p ./changed-commits")
        def file = "./changed-commits/"+ projectName
        sh("echo "+ commitId +" > "+ file)
        sh("git add "+ file)
        try{
            sh("git commit -m update-hash-" + projectName)
            sh("git push origin " + branchName)
        }catch(ignored){
            // this will happen if no change was detected
        }
    }
}

private boolean remoteBranchExists(String repository, String branch){
    def branchExists = sh(
            script: 'git ls-remote --heads ' + repository + " "+ branch +" | wc -l",
            returnStdout: true
    ).trim()
    echo branchExists
    return branchExists == '1'
}

private String getLastCommitId(String repository, String branch){
    def commitId = sh(
        script: "git ls-remote "+ repository + " "+ branch  + " | cut -f1",
        returnStdout: true
    ).trim()
    return commitId
}

private String determineRepoName() {
    return scm.getUserRemoteConfigs()[0].getUrl().tokenize('/').last().split("\\.")[0]
}

private String determineRepoUrl(){
    String repo = scm.userRemoteConfigs[0].url
    if(repo.startsWith("https://github.com/")){
        return repo.replaceFirst("https://github.com/", "git@github.com:")
    }else{
        return repo
    }
}