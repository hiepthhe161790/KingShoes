/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Category;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trinh
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getTop3() {
        List<Product> list = new ArrayList<>();
        String query = "select top 6 * from product";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getNext3Product(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT *\n"
                + "  FROM product\n"
                + " ORDER BY id\n"
                + "OFFSET ? ROWS\n"
                + " FETCH NEXT 3 ROWS ONLY";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where cateID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductBySellID(int id) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where sell_ID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where [name] like ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductByID(String id) {
        String query = "select * from product\n"
                + "where id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getLast() {
        String query = "select top 1 * from product\n"
                + "order by id desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account login(String user, String pass) {
        String query = "select * from account\n"
                + "where [user] = ?\n"
                + "and pass = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account checkAccountExist(String user) {
        String query = "select * from account\n"
                + "where [user] = ?\n";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void singup(String user, String pass) {
        String query = "insert into account\n"
                + "values(?,?,0,0)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteProduct(String pid) {
        String query = "delete from product\n"
                + "where id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertProduct(String name, String image, String price,
            String title, String description, String category, int sid) {
        String query = "INSERT [dbo].[product] \n"
                + "([name], [image], [price], [title], [description], [cateID], [sell_ID])\n"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setInt(7, sid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editProduct(String name, String image, String price,
            String title, String description, String category, String pid) {
        String query = "update product\n"
                + "set [name] = ?,\n"
                + "[image] = ?,\n"
                + "price = ?,\n"
                + "title = ?,\n"
                + "[description] = ?,\n"
                + "cateID = ?\n"
                + "where id = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setString(7, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    
    //có the xoa cua chatGPT
        public Product getProduct(String id) {
        // Thực hiện các bước để truy xuất sản phẩm từ cơ sở dữ liệu dựa trên ID

        // Giả sử sử dụng JDBC để truy vấn cơ sở dữ liệu
    String query = "select * from product\n"
                + "where id = ?";

        try {
          conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            // Kiểm tra kết quả trả về
            
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        1);
            }
        } catch (Exception e) {
            // Xử lý exception (nếu cần thiết)
      
        }
        return null;
    
    }
        
         // Lay tat ca danh sach account
         public List<Account> getAllAccounts() {
    List<Account> list  = new ArrayList<>();
    String query = "SELECT * FROM Account";
    try {
        conn = new DBContext().getConnection(); // Mở kết nối với cơ sở dữ liệu
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {  
            list.add(new Account(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)));
        }
    } catch (Exception e) {
  
    }
    return list ;
}
    
public void editAccount(String user, String pass, String isSell, String isAdmin, String uid) {
    String query = "UPDATE Account\n"
            + "SET [user] = ?,\n"
            + "pass = ?,\n"
            + "isSell = ?,\n"
            + "isAdmin = ?\n"
            + "WHERE uid = ?";
    try {
        conn = new DBContext().getConnection(); // Mở kết nối với cơ sở dữ liệu
        ps = conn.prepareStatement(query);
        ps.setString(1, user);
        ps.setString(2, pass);
        ps.setString(3, isSell);
        ps.setString(4, isAdmin);
        ps.setString(5, uid);
        ps.executeUpdate();
        conn.close(); // Đóng kết nối sau khi thực hiện câu truy vấn
    } catch (Exception e) {
        e.printStackTrace(); // In ra thông tin lỗi nếu có
    }
}


    public void updateAccount(Account account) {
    String xSql = "UPDATE Account SET user=?, pass=?, isSell=?, isAdmin=? WHERE id=?";
    try {
        conn = new DBContext().getConnection();//mo ket noi voi sql
        ps = conn.prepareStatement(xSql);
        ps.setString(1, account.getUser());
        ps.setString(2, account.getPass());
        ps.setInt(3, account.getIsSell());
        ps.setInt(4, account.getIsAdmin());
        ps.setInt(5, account.getId());
        ps.executeUpdate();
        ps.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
}
public Account getAccountByID(String uid) {
    String query = "SELECT * FROM account\n" +
                   "WHERE uid = ?";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, uid);
        rs = ps.executeQuery();
        while (rs.next()) {
            return new Account(rs.getInt(1),
                               rs.getString(2),
                               rs.getString(3),
                               rs.getInt(4),
                               rs.getInt(5));
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ (exception) tại đây (vd: in thông báo lỗi, ghi log, v.v.)
    }
    return null;
}

public void deleteAccount(String aid) {
        String query = "delete from Account\n"
                + "where uID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, aid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertAccount(String user, String pass, String isSell, String isAdmin) {
        String query = "INSERT Account\n"
                + "([user], [pass], [isSell], [isAdmin])\n"
                + "VALUES(?,?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, isSell);
            ps.setString(4, isAdmin);;
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();

        for (Category o : listC) {
            System.out.println(o);
        }
        
        
        //check ham get product
          String s = "8"; // Thay thế "123" bằng ID sản phẩm bạn muốn truy vấn
        Product product = dao.getProduct(s);
        
        if (product != null) {
            System.out.println("Product found: " + product.getName());
        } else {
            System.out.println("Product not found.");
       
    }
//     List<Account> accounts = dao.getAllAccounts();
//    for (Account account : accounts) {
//        System.out.println(account);
//    }   


   
//     String user = "hiep";
//    String pass = "hiep";
//    String isSell = "0";
//    String isAdmin = "0";
//    String uid = "31"; // Giả sử id của tài khoản cần chỉnh sửa là "123"
//
//    // Gọi hàm editAccount với các tham số tương ứng
//    dao.editAccount(user, pass, isSell, isAdmin, uid);

//    System.out.println("Account updated successfully.");
   String id = "28"; // Giả sử id bạn muốn kiểm tra là "123"
    Account account = dao.getAccountByID(id);
    if (account != null) {
        System.out.println("Account found:");
        System.out.println("ID: " + account.getId());
        System.out.println("User: " + account.getUser());
        System.out.println("Pass: " + account.getPass());
        System.out.println("IsSell: " + account.getIsSell());
        System.out.println("IsAdmin: " + account.getIsAdmin());
    } else {
        System.out.println("Account not found.");
    }  
//      String user = "vietanh";
//        String pass = "123";
//        String isSell = "0";
//        String isAdmin = "0";
//        
//
//        dao.insertAccount(user, pass, isSell, isAdmin);
//        
//        System.out.println("Account inserted successfully!");
         String uID = "36";
 dao.deleteAccount(uID);
   System.out.println("Account delete successfully!");
}

} 
   

