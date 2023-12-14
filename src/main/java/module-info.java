module me.reidj.halltest {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens me.reidj.halltest to javafx.fxml;
    opens me.reidj.halltest.controller.authorization to javafx.fxml;

    exports me.reidj.halltest;
    exports me.reidj.halltest.controller.authorization;
    exports me.reidj.halltest.controller;
}