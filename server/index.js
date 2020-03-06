const express = require('express')
const Repository = require('./model/Repository')
const User = require('./model/Repository')

const app = express()
const port = 3000

app.get('/search/repositories', Repository.find)
app.get('/users/:user', User.find)

app.listen(port, () => console.log(`Mocking GitHub API on port ${port}!`))
