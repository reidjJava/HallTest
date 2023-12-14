module me.reidj.halltest {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.google.gson;

    opens me.reidj.halltest to javafx.fxml;
    opens me.reidj.halltest.controller.authorization to javafx.fxml;
    opens me.reidj.halltest.controller.question to javafx.fxml;
    opens me.reidj.halltest.question to com.google.gson;

    exports me.reidj.halltest;
    exports me.reidj.halltest.controller.authorization;
    exports me.reidj.halltest.controller;
    exports me.reidj.halltest.controller.question;
}