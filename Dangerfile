# Ignore inline messages which lay outside a diff's range of PR
github.dismiss_out_of_range_messages

# ktlint
checkstyle_format.base_path = Dir.pwd
checkstyle_format.report 'app/build/reports/ktlint/ktlint-developmentDebug.xml'
# AndroidLint
android_lint.report_file = "app/build/reports/lint-results-developmentDebug.xml"
android_lint.skip_gradle_task = true
android_lint.severity = "Error"
android_lint.lint(inline_mode: true)
# Apk Analyzer
apkanalyzer.apk_file = "app/build/outputs/apk/production/release/app-*-production-release-unsigned.apk"
apkanalyzer.command_apkanalyzer="usr/local/android-sdk/tools/bin/apkanalyzer"
apkanalyzer.file_size
apkanalyzer.permissions
apkanalyzer.method_references
