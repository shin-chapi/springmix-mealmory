# Java Spring Bootでフロントを楽しく開発するサンプルプロジェクト

### はじめに

JavaのWebアプリケーションは、サーバーサイドで動作するバックエンドのアプリです。

しかし、実務ではフロント(CSSやJavaScript)の開発も欠かせません。

いちいちCDNでリンクを張って必要なモジュールを読み込んでいては、テンプレートの可読性が下がるばかりか、

ネットの通信回数と通信量が増え、画面表示に時間がかかったりと、デメリットが多くなります。

そこで、JavaScriptやCSSのモジュールを一元管理し、コンパクトにまとめて使いやすいように変換してくれるツールが登場しました。

それがWebpackです。

Webpackはサーバーサイドで動作する、今や大人気のJavaScriptのフレームワークです。

ただ、Webpackの設定ファイルであるwebpack.config.jsが結構煩雑な内容で、初心者向けではありません。

なんとかならないかとネットを検索していたら、こんなGitHubを見つけました。


[spring-boot-pebble-laravel-mix](https://github.com/flof/spring-boot-pebble-laravel-mix)

元々はphpのlaravelというフレームワーク向けに開発されたlaravel-mixを組み合わせることで、さらに手軽に使えるようにしようという試みです。

残念ながらそのままでは動作してくれませんでしたが、色々と手直しをしたところ、

app.jsとapp.cssを読み込むだけで、bootstrap、Sass、jQuery、Vueといった代表的なCSSやJavaScriptのフレームワークが、そのまま直感的に使えるようになりました。

煩わしい設定は必要ありません。

各ページ用に新しくscssファイルを作成する場合でも、webpack.mix.jsに一行追加するだけで、webpackのコンパイルが可能です。

オリジナルアプリ開発の一助になれば幸いです。
        

### 導入方法

 [このプロジェクトをHerokuにデプロイしたサンプルページ](https://sprimgmix.herokuapp.com/)
 
 こちらのサンプルページの項目は、各々どんなツールなのかの説明や使い方の参考サイトへのリンクになっています。
 
※Windowsの方は管理者用コマンドプロンプトで作業して下さい

1. 上記Nodeの参考サイトで、nvmとNode(npm)をインストールして下さい。Nodeのバージョンは、v16.13.2を選んでください。
 
1. cdで、eclipseのワークスペース直下に移動してください。
 
1. git clone https://github.com/MutsumiMatsuda/springmix あなたのプロジェクト名
 
1. SpringMixApplication.javaを、あなたのプロジェクトに合わせてリファクタリングして下さい。
 
1. GitHubにリモートリポジトリを作って下さい。
 
1. git remote set-url origin コマンドで、あなたのリモートリポジトリをプロジェクトに設定して下さい。
 
1. npm install
 
1. npm run dev
 
1. eclipseの4でリファクタリングしたjavaファイルを選択し、実行->Spring Bootアプリケーション
 
1. localhost:8080にブラウザでアクセスし、動作確認して下さい。

Enjoy your coding!