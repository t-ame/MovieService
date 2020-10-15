
<!DOCTYPE html>
<html lang="en-US">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Begin Jekyll SEO tag v2.6.1 -->
<title>Starwars Movie Service | MovieService</title>
<meta name="generator" content="Jekyll v3.9.0" />
<meta property="og:title" content="Starwars Movie Service" />
<meta property="og:locale" content="en_US" />
<link rel="canonical" href="https://t-ame.github.io/MovieService/" />
<meta property="og:url" content="https://t-ame.github.io/MovieService/" />
<meta property="og:site_name" content="MovieService" />
<script type="application/ld+json">
{"@type":"WebSite","headline":"Starwars Movie Service","url":"https://t-ame.github.io/MovieService/","name":"MovieService","@context":"https://schema.org"}</script>
<!-- End Jekyll SEO tag -->

    <link rel="stylesheet" href="https://t-ame.github.io/MovieService/assets/css/style.css?v=d850aca28a4610916c012bf3452efef4037d5725">
  </head>
  <body>
    <div class="container-lg px-3 my-5 markdown-body">
      
      <h1><a href="https://t-ame.github.io/MovieService/">MovieService</a></h1>
      

      <h1 id="starwars-movie-service">Starwars Movie Service</h1>

<p>A web service to list the names of Star Wars movies along with their opening crawls and character. Also, anonymous comments can also be added and retrieved.</p>

<p>The <strong>Base URL</strong> for this application is <a href="https://www.google.com">https://www.google.com</a></p>

<p><br /></p>

<h4 id="content-list">Content List</h4>

<ul>
  <li><a href="#response-format-info">Response Format Info</a></li>
  <li><a href="#movies">Movies</a></li>
  <li><a href="#characters">Characters</a></li>
  <li><a href="#comments">Comments</a></li>
</ul>

<p><br /></p>

<h4 id="response-format-info">Response Format Info</h4>

<p><strong>ERROR</strong></p>

<p>Status Code: <strong>500</strong></p>

<blockquote>
  <p>Response Body:</p>
</blockquote>

<div class="language-json highlighter-rouge"><div class="highlight"><pre class="highlight"><code><span class="p">{</span><span class="w">
    </span><span class="nl">"error_message"</span><span class="p">:</span><span class="w"> </span><span class="s2">"An error occured. Please try again later."</span><span class="w">
</span><span class="p">}</span><span class="w">
</span></code></pre></div></div>

<p><strong>SUCCESS</strong></p>

<p>Status Code: <strong>200</strong></p>

<blockquote>
  <p>Response Body: As specified for each endpoint below</p>
</blockquote>

<p><br /></p>

<h4 id="movies">Movies</h4>

<p><strong>Fetch list of movies with data</strong></p>

<blockquote>
  <p><strong>GET</strong> <em>/starwars/movies</em></p>
</blockquote>

<blockquote>
  <p>Response Body:</p>
</blockquote>

<div class="language-json highlighter-rouge"><div class="highlight"><pre class="highlight"><code><span class="p">{</span><span class="w">
    </span><span class="nl">"result"</span><span class="p">:</span><span class="w"> </span><span class="p">[</span><span class="w">
        </span><span class="p">{</span><span class="w">
            </span><span class="nl">"title"</span><span class="p">:</span><span class="w"> </span><span class="s2">"A New Hope"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"opening_crawl"</span><span class="p">:</span><span class="w"> </span><span class="s2">"It is a period of civil war.</span><span class="se">\r\n</span><span class="s2">Rebel spaceships, striking</span><span class="se">\r\n</span><span class="s2">from a hidden base, have won</span><span class="se">\r\n</span><span class="s2">their first victory against</span><span class="se">\r\n</span><span class="s2">the evil Galactic Empire.</span><span class="se">\r\n\r\n</span><span class="s2">During the battle, Rebel</span><span class="se">\r\n</span><span class="s2">spies managed to steal secret</span><span class="se">\r\n</span><span class="s2">plans to the Empire's</span><span class="se">\r\n</span><span class="s2">ultimate weapon, the DEATH</span><span class="se">\r\n</span><span class="s2">STAR, an armored space</span><span class="se">\r\n</span><span class="s2">station with enough power</span><span class="se">\r\n</span><span class="s2">to destroy an entire planet.</span><span class="se">\r\n\r\n</span><span class="s2">Pursued by the Empire's</span><span class="se">\r\n</span><span class="s2">sinister agents, Princess</span><span class="se">\r\n</span><span class="s2">Leia races home aboard her</span><span class="se">\r\n</span><span class="s2">starship, custodian of the</span><span class="se">\r\n</span><span class="s2">stolen plans that can save her</span><span class="se">\r\n</span><span class="s2">people and restore</span><span class="se">\r\n</span><span class="s2">freedom to the galaxy...."</span><span class="p">,</span><span class="w">
            </span><span class="nl">"release_date"</span><span class="p">:</span><span class="w"> </span><span class="s2">"1977-05-25"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"commentCount"</span><span class="p">:</span><span class="w"> </span><span class="mi">0</span><span class="p">,</span><span class="w">
            </span><span class="nl">"comments"</span><span class="p">:</span><span class="w"> </span><span class="s2">"/movies/4/comments"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"charactersURL"</span><span class="p">:</span><span class="w"> </span><span class="s2">"/movies/4/characters"</span><span class="w">
        </span><span class="p">}</span><span class="w">
    </span><span class="p">],</span><span class="w">
    </span><span class="nl">"count"</span><span class="p">:</span><span class="w"> </span><span class="mi">1</span><span class="p">,</span><span class="w">
    </span><span class="nl">"self"</span><span class="p">:</span><span class="w"> </span><span class="s2">"{baseURL}/starwars/movies"</span><span class="w">
</span><span class="p">}</span><span class="w">
</span></code></pre></div></div>

<p><br /></p>

<h4 id="characters">Characters</h4>

<p><strong>Fetch list of characters in a movie</strong></p>

<blockquote>
  <p><strong>GET</strong> <em>/starwars/movies/{episodeID}/characters</em></p>
</blockquote>

<blockquote>
  <p>Query Parameters:</p>
</blockquote>

<blockquote>
  <ul>
    <li><strong>filtergender</strong>: Filter by gender. e.g</li>
  </ul>
</blockquote>

<blockquote>
  <blockquote>
    <p><code class="language-plaintext highlighter-rouge">male, female, hermaphrodite, etc.</code></p>
  </blockquote>
</blockquote>

<blockquote>
  <ul>
    <li><strong>sortfield</strong>: Sort by field. Options include;</li>
  </ul>
</blockquote>

<blockquote>
  <blockquote>
    <p><code class="language-plaintext highlighter-rouge">name, gender, height</code></p>
  </blockquote>
</blockquote>

<blockquote>
  <ul>
    <li><strong>sortdirection</strong>: Sorting direction. Options include;</li>
  </ul>
</blockquote>

<blockquote>
  <blockquote>
    <p><code class="language-plaintext highlighter-rouge">asc, desc</code></p>
  </blockquote>
</blockquote>

<blockquote>
  <p>Response Body:</p>
</blockquote>

<div class="language-json highlighter-rouge"><div class="highlight"><pre class="highlight"><code><span class="p">{</span><span class="w">
    </span><span class="nl">"result"</span><span class="p">:</span><span class="w"> </span><span class="p">[</span><span class="w">
        </span><span class="p">{</span><span class="w">
            </span><span class="nl">"name"</span><span class="p">:</span><span class="w"> </span><span class="s2">"Wilhuff Tarkin"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"height"</span><span class="p">:</span><span class="w"> </span><span class="mi">180</span><span class="p">,</span><span class="w">
            </span><span class="nl">"mass"</span><span class="p">:</span><span class="w"> </span><span class="s2">"unknown"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"hair_color"</span><span class="p">:</span><span class="w"> </span><span class="s2">"auburn, grey"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"skin_color"</span><span class="p">:</span><span class="w"> </span><span class="s2">"fair"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"eye_color"</span><span class="p">:</span><span class="w"> </span><span class="s2">"blue"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"birth_year"</span><span class="p">:</span><span class="w"> </span><span class="s2">"64BBY"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"gender"</span><span class="p">:</span><span class="w"> </span><span class="s2">"male"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"homeworld"</span><span class="p">:</span><span class="w"> </span><span class="s2">"http://swapi.dev/api/planets/21/"</span><span class="w">
        </span><span class="p">}</span><span class="w">
    </span><span class="p">],</span><span class="w">
    </span><span class="nl">"totalCount"</span><span class="p">:</span><span class="w"> </span><span class="mi">18</span><span class="p">,</span><span class="w">
    </span><span class="nl">"totalMatch"</span><span class="p">:</span><span class="w"> </span><span class="mi">1</span><span class="p">,</span><span class="w">
    </span><span class="nl">"totalheighCm"</span><span class="p">:</span><span class="w"> </span><span class="mi">3066</span><span class="p">,</span><span class="w">
    </span><span class="nl">"totalheighFt"</span><span class="p">:</span><span class="w"> </span><span class="s2">"100"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"totalheighIn"</span><span class="p">:</span><span class="w"> </span><span class="s2">"7.09"</span><span class="p">,</span><span class="w">
    </span><span class="nl">"self"</span><span class="p">:</span><span class="w"> </span><span class="s2">"{baseURL}/starwars/movies/4/characters"</span><span class="w">
</span><span class="p">}</span><span class="w">
</span></code></pre></div></div>

<p><br /></p>

<h4 id="comments">Comments</h4>

<p><strong>Add New Comment to a movie</strong></p>

<blockquote>
  <p><strong>POST</strong> <em>/starwars/movies/{episodeID}/comments</em></p>
</blockquote>

<blockquote>
  <p>Request Body:</p>
</blockquote>

<div class="language-json highlighter-rouge"><div class="highlight"><pre class="highlight"><code><span class="p">{</span><span class="w">
	</span><span class="nl">"comment"</span><span class="p">:</span><span class="w"> </span><span class="s2">"A comment that is not more than 500 words"</span><span class="w">
</span><span class="p">}</span><span class="w">
</span></code></pre></div></div>

<blockquote>
  <p>Response Body:</p>
</blockquote>

<div class="language-json highlighter-rouge"><div class="highlight"><pre class="highlight"><code><span class="p">{</span><span class="w">
    </span><span class="nl">"comment"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">
        </span><span class="nl">"commentId"</span><span class="p">:</span><span class="w"> </span><span class="mi">3</span><span class="p">,</span><span class="w">
        </span><span class="nl">"movieId"</span><span class="p">:</span><span class="w"> </span><span class="mi">4</span><span class="p">,</span><span class="w">
        </span><span class="nl">"comment"</span><span class="p">:</span><span class="w"> </span><span class="s2">"A comment that is not more than 500 words"</span><span class="p">,</span><span class="w">
        </span><span class="nl">"ipAddress"</span><span class="p">:</span><span class="w"> </span><span class="s2">"0:0:0:0:0:0:0:1"</span><span class="w">
    </span><span class="p">},</span><span class="w">
    </span><span class="nl">"self"</span><span class="p">:</span><span class="w"> </span><span class="s2">"{baseURL}/starwars/movies/4/comments/3"</span><span class="w">
</span><span class="p">}</span><span class="w">
</span></code></pre></div></div>

<p><strong>Fetch list of all anonymous comments made on a movie</strong></p>

<blockquote>
  <p><strong>GET</strong> <em>/starwars/movies/{episodeID}/comments</em></p>
</blockquote>

<blockquote>
  <p>Response Body:</p>
</blockquote>

<div class="language-json highlighter-rouge"><div class="highlight"><pre class="highlight"><code><span class="p">{</span><span class="w">
    </span><span class="nl">"result"</span><span class="p">:</span><span class="w"> </span><span class="p">[</span><span class="w">
        </span><span class="p">{</span><span class="w">
            </span><span class="nl">"commentId"</span><span class="p">:</span><span class="w"> </span><span class="mi">2</span><span class="p">,</span><span class="w">
            </span><span class="nl">"movieId"</span><span class="p">:</span><span class="w"> </span><span class="mi">4</span><span class="p">,</span><span class="w">
            </span><span class="nl">"comment"</span><span class="p">:</span><span class="w"> </span><span class="s2">"Another test comment"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"date_time"</span><span class="p">:</span><span class="w"> </span><span class="s2">"2020-10-15 02:13:25"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"ipAddress"</span><span class="p">:</span><span class="w"> </span><span class="s2">"0:0:0:0:0:0:0:1"</span><span class="p">,</span><span class="w">
            </span><span class="nl">"self"</span><span class="p">:</span><span class="w"> </span><span class="s2">"{baseURL}/starwars/movies/4/comments/2"</span><span class="w">
        </span><span class="p">}</span><span class="w">
    </span><span class="p">],</span><span class="w">
    </span><span class="nl">"count"</span><span class="p">:</span><span class="w"> </span><span class="mi">1</span><span class="p">,</span><span class="w">
    </span><span class="nl">"self"</span><span class="p">:</span><span class="w"> </span><span class="s2">"{baseURL}/starwars/movies/4/comments"</span><span class="w">
</span><span class="p">}</span><span class="w">
</span></code></pre></div></div>

<p><strong>Fetch a comment by comment ID</strong></p>

<blockquote>
  <p><strong>GET</strong> <em>/starwars/movies/{episodeID}/comments/{commentID}</em></p>
</blockquote>

<blockquote>
  <p>Response Body:</p>
</blockquote>

<div class="language-json highlighter-rouge"><div class="highlight"><pre class="highlight"><code><span class="p">{</span><span class="w">
    </span><span class="nl">"comment"</span><span class="p">:</span><span class="w"> </span><span class="p">{</span><span class="w">
        </span><span class="nl">"commentId"</span><span class="p">:</span><span class="w"> </span><span class="mi">1</span><span class="p">,</span><span class="w">
        </span><span class="nl">"movieId"</span><span class="p">:</span><span class="w"> </span><span class="mi">4</span><span class="p">,</span><span class="w">
        </span><span class="nl">"comment"</span><span class="p">:</span><span class="w"> </span><span class="s2">"A test comment"</span><span class="p">,</span><span class="w">
        </span><span class="nl">"date_time"</span><span class="p">:</span><span class="w"> </span><span class="s2">"2020-10-15 02:00:49"</span><span class="p">,</span><span class="w">
        </span><span class="nl">"ipAddress"</span><span class="p">:</span><span class="w"> </span><span class="s2">"0:0:0:0:0:0:0:1"</span><span class="w">
    </span><span class="p">},</span><span class="w">
    </span><span class="nl">"self"</span><span class="p">:</span><span class="w"> </span><span class="s2">"{baseURL}/starwars/movies/4/comments/1"</span><span class="w">
</span><span class="p">}</span><span class="w">
</span></code></pre></div></div>



      
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/anchor-js/4.1.0/anchor.min.js" integrity="sha256-lZaRhKri35AyJSypXXs4o6OPFTbTmUoltBbDCbdzegg=" crossorigin="anonymous"></script>
    <script>anchors.add();</script>
    
  </body>
</html>
