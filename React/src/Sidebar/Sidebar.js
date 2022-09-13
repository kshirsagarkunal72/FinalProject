import React, { useState } from 'react';
import {FaHome,FaBars,FaPeopleCarry} from 'react-icons/fa'
import {FcBusinessman,FcContacts} from 'react-icons/fc'
import {BsBasket,BsFillPersonLinesFill,BsShieldLock} from 'react-icons/bs'
// import {BiSearch} from 'react-icons/bi'
import {AiOutlineUserAdd} from 'react-icons/ai'
import {IoIosPeople,IoIosLogOut} from 'react-icons/io'

import { NavLink } from 'react-router-dom';
import {motion,AnimatePresence} from "framer-motion"
const routes=[
    {
        path:"/",
        name:"Home",
        icon:<FaHome/>,
    },
    {
        path:"/",
        name:"Profile",
        icon:<FcBusinessman/>,
    },
    {
        path:"/",
        name:"Orders",
        icon:<BsBasket/>,
    },
    {
        path:"/",
        name:"Customers",
        icon:<IoIosPeople/>,
    },
    {
        path:"/",
        name:"Suppliers",
        icon:<FaPeopleCarry/>,
    },
    {
        path:"/",
        name:"Admins",
        icon:<BsFillPersonLinesFill/>,
    },
    {
        path:"/",
        name:"Add Admin",
        icon:<AiOutlineUserAdd/>,
    },
    {
        path:"/",
        name:"Change Password",
        icon:<BsShieldLock/>,
    },
    {
        path:"/",
        name:"Contact Us",
        icon:<FcContacts/>,
    },
    {
        path:"/",
        name:"Logout",
        icon:<IoIosLogOut/>,
    },
]

const Sidebar = ({children}) => {

    const[isOpen,setIsOpen]=useState(false);

    const toggle=()=>setIsOpen(!isOpen)

    // const inputAnimation={
    //     hidden:{
    //         width:0,
    //         padding:0,
    //         opacity:0,
    //         Transition:{
    //             duration:0.2,
    //         },
    //     },
    //     show:{
    //         width:"140px",
    //         padding:"5px 15px",
    //         opacity:1,
    //         Transition:{
    //             duration:0.2,
    //         },
    //     },
    // };

    const showAnimation={
        hidden:{
            width:0,
            opacity:0,
            Transition:{
                duration:0.5,
            },
        },
        show:{
            width:"auto",
            opacity:1,
            Transition:{
                duration:0.2,
            },
        },
    };

  return (
    <div className='main-container'>
      <motion.div animate={{width:isOpen?"200px":"35px"}} className="sidebar">
          <div className='top_section'>
              {isOpen && <h1 className='logo'>WISH IT</h1>}
              <div className='bars'>
                  <FaBars onClick={toggle}/>
              </div>
          </div>
          {/* <div className='search'>
              <div className='search_icon'>
                  <BiSearch/>
              </div>
              <AnimatePresence>
              {isOpen && (
               <motion.input 
               initial="hidden"
               animate="show"
               exit="hidden"
               variants={inputAnimation} placeholder='Search...'></motion.input>
               )}
              </AnimatePresence>
          </div> */}
          <section className='routes'>
              {routes.map((route)=>(
                  <NavLink to={route.path} key={route.name} className="linked">
                      <div className='icon'>
                          {route.icon}
                      </div>
                     <AnimatePresence>
                     {isOpen && <motion.div
                     variants={showAnimation}
                     initial="hidden"
                     animate="show"
                     exit="hidden"
                     className='link_text'>
                          {route.name}
                      </motion.div>}
                     </AnimatePresence>
                  </NavLink>
              ))}
          </section>
      </motion.div>
      <main>{children}</main>
    </div>
  )
}

export default Sidebar