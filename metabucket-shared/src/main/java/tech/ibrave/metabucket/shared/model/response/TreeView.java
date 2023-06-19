//package tech.ibrave.metabucket.shared.model.response;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Author: hungnm
// * Date: 18/06/2023
// */
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class TreeView<T> {
//
//    public TreeView(T id) {
//        this.id = id;
//    }
//
//    private T id;
//    private T parentId;
//    @JsonIgnore
//    private TreeView parent;
//
//    private List<TreeView> children = new ArrayList<>(5);
//
//}
