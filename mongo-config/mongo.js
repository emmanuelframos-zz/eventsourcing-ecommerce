db.createUser(
    {
      user: "app",
      pwd:  "app2019",
      roles: [
         { role: "readWrite", db: "ecommerce" }
      ]
    }
)