package com.example.myapplication

class MyDataModel(id: String?, firstname: String?, lastname: String?, gr: String?) {
    //Реализация модели наших данных для использования в дальнейшем
    var firstname: String? = firstname
        private set
    var lastname: String? = lastname
        private set
    var gr: String? = gr
        private set
    var id: String? = id
        private set

}