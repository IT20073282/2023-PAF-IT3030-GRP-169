import react,{component}from 'react';
import'bootstrap/dist/css/bootstrap.min.css';
import{browserrouter as router,switch,route,link}
    from "react-router-dom";

import profile from './components/profile.component';
import post from './components/post.component';
import comment from './components/comment.component';
class app extends component{
  render(){
    return(
      <router>
        <div classname ="container">
          <nav classname="navbar navbar-expand-lgnavbar-light bg-light">
            <link to={'/'}classname ="navbar-brand">
              FOODY
            </link>
            <div>
              classname="collapse navbar-collapse"
              id="navbarsupported content"
              <ul> class name='navbar-navmr-auto'</ul>
              <li><link to={'/'} classname="navlink"
              home </link> 
              </li>
              <li><link to ={'/create'}classname ="navlink">create</link>
             </li>
             </ul>

            </div>
          </nav></br>
          <h2>KINDLY GIVE YOUR COMMENTS</h2></br>
          <switch>
            <route exact path='/profile 'component ={profile}/>
            <route exact path='/post/:id'component ={post}/>

          <route exact path='/comment'component={comment}/>

          </switch>
        </div>
      </router>
    );
  }
}

export default app;