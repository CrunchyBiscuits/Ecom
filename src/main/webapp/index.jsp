<html>
<body>
<h2>Login</h2>
<form method="post" action="login">
    Email:<input type="text" name="email"><br>
    Password:<input type="password" name="password"><br>
    <input type="submit" value="Login">
</form>

<h2>Delete</h2>
<form method="post" action="delete">
    Input the Email for the account you want to delete:
    <br/>
    <input type="text" name="email"/>
    <br/>
    <input type="submit" value="submit"/>
</form>

<h2>register</h2>
<form method="post" action="register">
    Email:<input type="text" name="email"/>
    <br/>
    Name:<input type="text" name="name"/>
    <br/>
    Password:<input type="password" name="password"/>
    <br/>
    Actual Name:<input type="text" name="actual_name"/>
    <br/>
    Phone:<input type="text" name="phone"/>
    <br/>
    Gender:
    <input type="radio" name="gender" value="0" />Male
    <input type="radio" name="gender" value="1"/>Female
    <br/>
    <input type="submit" value="submit"/>
</form>

<h2>update</h2>
<form method="post" action="update">
    Email:<input type="text" name="email"/>
    <br/>
    Name:<input type="text" name="name"/>
    <br/>
    Phone:<input type="text" name="phone"/>
    <br/>
    <input type="submit" value="submit"/>
</form>

<h2>add profile</h2>
<form action="profile" method="post"
      enctype="multipart/form-data">
    <table  style="height: 100px" cellpadding="20px">
        <tr>
            <th style="vertical-align: middle">Email</th>
            <td style="vertical-align: middle"><input name="email" type="text"></td>
        </tr>
        <tr>
            <th style="vertical-align: middle">Profile</th>
            <td style="vertical-align: middle"><input name="profile" type="file" accept="image/*"></td>
        </tr>
    </table>
    <div>
        <input class="btn btn-info col-lg-8" type="submit" value="Add"
               style="width: 200px">
    </div>
</form>

<h2>show profile</h2>
<form method="POST" action="showprofile">
    Email:<input type="text" name="email"><br>
    <input type="submit" value="Show User profile">
</form>


<h2>add product image</h2>
<form method="POST" action="addproduct" enctype="multipart/form-data">
    product name:<input type="text" name="product_name"><br>
    unit price:<input type="text" name="unit_price"><br>
    details:<input type="text" name="details"><br>
    add date:<input type="date" name="add_date"><br>
    stock:<input type="text" name="stock"><br>
    sales:<input type="text" name="sales"><br>
    status:<input type="text" name="status"><br>
    <input name="image1" type="file" accept="image/*">
    <input name="image2" type="file" accept="image/*">
    <input name="image3" type="file" accept="image/*"><br>
    <input type="submit" value="Add Product">
</form>


<h2>show product profile</h2>
<form method="POST" action="showprofile">
    Email:<input type="text" name="product_id"><br>
    <input type="submit" value="Show item images">
</form>
<br><br><br>
</body>
</html>
