module.exports = function (grunt) {
    grunt.initConfig({
        jasmine: {
            test: {
                src: 'src/main/javascript/*.js',
                options: {
                    specs: 'src/test/javascript/*spec.js'
                }
            }
        }
    })
    grunt.loadNpmTasks('grunt-contrib-jasmine')
    grunt.registerTask('default', ['jasmine']);
};