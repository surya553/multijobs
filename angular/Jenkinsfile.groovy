pipeline {
    agent any
    stages{
        stage('NPM Install') {
            steps{
                withEnv(["NPM_CONFIG_LOGLEVEL=warn"]) {
                    sh 'npm install'
                }
            }
        }    
        stage('Test') {
            steps{
                withEnv(["CHROME_BIN=/usr/bin/chromium-browser"]) {
                    sh 'ng test --progress=false --watch false'
                }
            }
        }    
        stage('Lint') {
            steps{
                sh 'ng lint'
            }
        }    
        stage('Build') {
            steps{   
                sh 'ng build --prod --aot --sm --progress=false'
            }
        }    
        stage('Archive') {
            steps{   
                echo "archive"
            }
        }    
        stage('Deploy') {
            steps{
                echo "Deploying..."
            }
        }
    }
}        
