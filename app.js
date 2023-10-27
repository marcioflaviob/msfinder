const express = require('express');
const cors = require('cors');

const app = express();

// Enable CORS
app.use(cors());

// Define your routes here
// For example:
app.get('/songsearch', (req, res) => {
  const { q } = req.query;
  // Perform your search logic and return the response
  res.json(/* Your JSON response here */);
});

// Start the server
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
