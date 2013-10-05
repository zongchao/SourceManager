package com.neko.controller;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import antlr.collections.List;

import com.neko.bean.Users;
import com.neko.service.interfaces.UserServiceInterface;

@Controller
@RequestMapping("/UserManager")
public class UserController 
{
    
    @Resource(name = "userService")
    UserServiceInterface userService;
    
    @RequestMapping("/userList")
    public ModelAndView userList()
    {
        ModelAndView mav = new ModelAndView("UserManager/userList");
        Collection<Users> userList = userService.getUserList();
        mav.addObject("userList", userList);
        return mav;
    }
    
    /*Collection<Users> userList;

    Users user;
    String captcha;
    String username;
    String oldUsername; // 修改前的用户名，用于判断用户名是否修改过
    Integer id;

    

    @Action(value="addUser", results={
            @Result(type="redirect", location="userList"),
            @Result(name="input", location="/UserManager/addUser.jsp" ),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String addUser()
    {
        if (StringUtil.IsNullOrWhiteSpace(user.getUsername()) || StringUtil.IsNullOrWhiteSpace(user.getPassword()))
        {
            addActionError("请确认用户名或密码是否输入");
            return INPUT;
        }
        if (userService.isUsernameExist(user.getUsername()))
        {
            addActionError("用户名已存在");
            return INPUT;
        }
        if (!CaptchaService.validate(captcha))
        {
            addActionError("请确认验证码错误");
            return INPUT;
        }
        if (userService.addUser(user))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }

    @Action(value="checkUsername", results={
            @Result(type="plainText", params={
                    "location", "/blank.jsp", "charSet", "UTF-8"
            })
    })
    public void checkUsername()
    {
        if (userService.isUsernameExist(username))
        {
            CommonUtil.printMessage(true);
        }
        else
        {
            CommonUtil.printMessage(false);
        }
    }

    @Action(value="forbidOrActivateUser", results={
            @Result(type="redirect", location="userList"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String forbidOrActivateUser()
    {
        if (userService.forbidOrActivateUser(id))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }

    @Action(value="delUser", results={
            @Result(type="redirect", location="userList"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String delUser()
    {
        if (userService.delUser(id))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }

    @Actions({
            @Action(value="modUserPre", results={
                    @Result(location="/UserManager/modUser.jsp"),
                    @Result(name="error", type="redirect", location="/error.jsp")
            }),
            @Action(value="userDetail", results={
                    @Result(location="/UserManager/userDetail.jsp"),
                    @Result(name="error", type="redirect", location="/error.jsp")
            })
    })
    public String findUserById()
    {
        if (id != null)
        {
            user = userService.findUserById(id);
            if (user == null)
            {
                return ERROR;
            }
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }

    }

    @Action(value="modUser", results={
            @Result(type="redirect", location="userList"),
            @Result(name="input", type="redirect", location="modUserPre?id=${user.id}" ),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String modUser()
    {
        if (StringUtil.IsNullOrWhiteSpace(user.getUsername()) || StringUtil.IsNullOrWhiteSpace(user.getPassword()))
        {
            addActionError("请确认用户名或密码是否输入");
            return INPUT;
        }
        if (!user.getUsername().equals(oldUsername) && userService.isUsernameExist(user.getUsername()))
        {
            addActionError("用户名已存在");
            return INPUT;
        }
        if (!CaptchaService.validate(captcha))
        {
            addActionError("请确认验证码错误");
            return INPUT;
        }
        if (userService.modUser(user))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }

    @Action(value="login", results={
        @Result(type="redirect", location="/UserManager/userList"),
        @Result(name="input", location="/login.jsp" ),
        @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String login()
    {
        Users currentUser = userService.findUserByUserInfo(user.getUsername(), user.getPassword());
        if (null == currentUser)
        {
            addActionError("请确认用户名或密码是否正确");
            return INPUT;
        }
        else if (currentUser.getIsDelete() == 1)
        {
            addActionError("用户已被禁用");
            return INPUT;
        }
        else
        {
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put("user", currentUser);
            // 更新最后登录时间
            currentUser.setLastLoginTime(new Date());
            userService.modUser(currentUser);
            return SUCCESS;
        }
    }

    @Action(value="logout", results={
        @Result(location="/login.jsp"),
        @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String logout()
    {
        try
        {
            Map<String, Object> session = ActionContext.getContext().getSession();
            if (session.containsKey("user"))
            {
                session.remove("user");
            }
        }
        catch (Exception e)
        {
            return ERROR;
        }
        return SUCCESS;
    }

    public Collection<Users> getUserList()
    {
        return userList;
    }

    public void setUserList(Collection<Users> userList)
    {
        this.userList = userList;
    }
    
    public Users getUser()
    {
        return user;
    }

    public void setUser(Users user)
    {
        this.user = user;
    }


    public UserServiceInterface getUserService()
    {
        return userService;
    }

    public void setUserService(UserServiceInterface userService)
    {
        this.userService = userService;
    }

    public String getCaptcha()
    {
        return captcha;
    }

    public void setCaptcha(String captcha)
    {
        this.captcha = captcha;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getOldUsername()
    {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername)
    {
        this.oldUsername = oldUsername;
    }

    @Override
    public void prepare() throws Exception
    {
        clearActionErrors();
    }*/

}
