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

サンプルデータを生成するライブラリ  
* Faker  
https://github.com/fzaninotto/Faker  
今回はPHPで作成しました。  
![2](https://user-images.githubusercontent.com/51936419/117618951-9c9c2400-b1a9-11eb-9a8b-16cbeedc5f1f.jpg)

# Installation
* Composer のインストール  
https://weblabo.oscasierra.net/php-composer-windows-install/
* fakerのインストール  
`composer require fzaninotto/faker`

# Note
* 現在Windows10でブラウザはGoogle ChromeとMicrosoft Edgeでのみ動作済み
* データベースはMySQLのみ対応

# Author
ishimaru-alt

# License
Enjoy making cute physics simulations!
 
Thank you!
