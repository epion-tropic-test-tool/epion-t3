# Epion-Tropic-Test-Tool 開発用プロジェクト

## このプロジェクトについて
このプロジェクトは、ETTTの開発を行うためのプロジェクトです。
配布であったり、アーカイブ対象ではありません。

そのため、各種ETTTのカスタムプロジェクトがサブモジュールとして追加されています。
このプロジェクトでは、IDE(IntelliJ等)と連動することによってデバッグを行えます。

## Documents
ユーザ向けドキュメントは以下で公開している。  
http://ettt.t-zomu.com/  
  
開発者向けドキュメントについては、このMarkdown上で書き表すこととする.

## モデリングの概念

本家ドキュメントに詳しく書いたためそちらを参照してもらいたい。


## 処理の概要フロー

処理は以下のような流れで実行される。

`Application`  
↓  
`ApplicationRunner`  
↓  
`Scenario`  
↓  
`Flow`  
↓  
`Command`  


### 処理説明

#### Application
`com.epion_t3.application.Application`にmainメソッドがあり、ツール起動はこのクラスから行う。
このクラスでは、`ApplicationRunner`を探す処理を入れているが、あんまり今のところ拡張予定はない・・・
`v1.0`とか`v2.0`とかとんでもない変更を入れたときとか、根元から動き替えたいときに助かるかなぁと妄想して作っているレベル。

#### ApplicationRunner

ApplicationRunnerの責務は以下となる。

- 引数解析
- ディレクトリ構成作成（結果ディレクトリ）
- シナリオ解析（YAMLを解析して保持する機構）
- シナリオ実行処理の起動
- 全体レポートの作成（各シナリオをまとめるレポート）
- ツール全体の例外ハンドリング処理

ApplicationRunnerレベルで動きの制御を行っている。  

#### ScenarioRunner

ScenarioRunnerの責務は以下となる。

- 実行すべきシナリオの解決
- 結果ディレクトリの作成（シナリオ毎にもディレクトリを作成するため）
- Flowの実行（シナリオはFlowの集合となる）
- Flowの実行制御（ NEXT | CHOICE | EXIT ）
- Global変数、Scenario変数、Profileのバインド

#### FlowRunner

FlowRunnerはいくつかに分かれており、それぞれに責務が異なる。


