const user = {
  "login": "jpventura",
  "id": 6794080,
  "node_id": "MDQ6VXNlcjY3OTQwODA=",
  "avatar_url": "https://avatars1.githubusercontent.com/u/6794080?v=4",
  "gravatar_id": "",
  "url": "https://api.github.com/users/jpventura",
  "html_url": "https://github.com/jpventura",
  "followers_url": "https://api.github.com/users/jpventura/followers",
  "following_url": "https://api.github.com/users/jpventura/following{/other_user}",
  "gists_url": "https://api.github.com/users/jpventura/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/jpventura/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/jpventura/subscriptions",
  "organizations_url": "https://api.github.com/users/jpventura/orgs",
  "repos_url": "https://api.github.com/users/jpventura/repos",
  "events_url": "https://api.github.com/users/jpventura/events{/privacy}",
  "received_events_url": "https://api.github.com/users/jpventura/received_events",
  "type": "User",
  "site_admin": false,
  "name": "JP Ventura",
  "company": null,
  "blog": "https://jpventura.com",
  "location": "Sao Paulo, Sao Paulo, Brazil",
  "email": null,
  "hireable": true,
  "bio": null,
  "public_repos": 127,
  "public_gists": 14,
  "followers": 40,
  "following": 19,
  "created_at": "2014-02-26T13:39:25Z",
  "updated_at": "2020-03-06T15:22:45Z"
}

module.exports = {
  findOne: (req, res) => Promise((resolve, reject) => {
    return res.send(req.param)
  })
}
  