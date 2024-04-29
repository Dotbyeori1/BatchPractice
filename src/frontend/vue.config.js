const { defineConfig } = require('@vue/cli-service');
const path = require('path');

module.exports = defineConfig({
  outputDir: path.resolve(__dirname, "../main/resources/static"),
  devServer: {
    port : 3000,
    proxy: {
      '/api': { // 예시 context 경로
        target: 'http://localhost', // 스프링부트 개발포트
        ws: true,
        changeOrigin: true
      }
    }
  }
});