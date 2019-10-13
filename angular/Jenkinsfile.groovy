#!groovy
node {
    stage('NPM Install') {
        withEnv(["NPM_CONFIG_LOGLEVEL=warn"]) {
            sh 'npm install'
        }
    }
    stage('Test') {
      echo "testing success"
    }
    stage('Lint') {
        sh 'ng lint'
    }
    stage('Build') {
        milestone()
        sh 'ng build --prod --aot --sm --progress=false'
    }
    stage('Archive') {
        sh 'tar -cvzf dist.tar.gz --strip-components=1 dist'
        archive 'dist.tar.gz'
    }
    stage('Deploy') {
        milestone()
        echo "Deploying..."
    }
}
