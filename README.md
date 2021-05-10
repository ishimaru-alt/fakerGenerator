# fakerGenerator
このシステムはテストをするときに必要なサンプルデータを自動で生成し、登録したデータベースに追加するシステムです。

# DEMO


# 機能

* ログイン
* サンプルデータ（ダミーデータ）の生成
* 指定したデータベースにデータを追加
* 生成したデータに合うようにテーブルの作成

# Requirement
JavaでJsonを取り扱うためのライブラリ
* gson-2.8.2.jar
* jackson-databind-2.11.2.jar
* joda-time-2.10.8.jar
* json-20200518.jar

MySQLを扱うためのライブラリ
* mysql-connector-java-5.1.22-bin.jar

Json（サンプルデータ）を生成するライブラリ  
https://github.com/fzaninotto/Faker  
今回はPHPで作成しました。  
