const repositories = require('./repositories')

module.exports = {
  find: (req, res) => {
    const page = req.query.page - 1 || 0
    const result = repositories[page]
  
    if (result) {
      res.status == 200
      return res.send(result)
    } else {
      res.status = 404
      return res.send()
    }
  }
}
