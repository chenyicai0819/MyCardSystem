const recommendEmail = (data) => {
  let out=`<div style="width: 600px;margin: 0 auto;">
    <includetail>
        <table style="text-align: center; font-size: 16px; color: #333333; border-spacing: 0px; border-collapse: collapse; width: 580px; direction: ltr">
            <tbody>
            <tr>
                <td style="font-size: 14px; padding: 0px 0px 7px 0px; text-align: center;color: #1FA2FF">
                   ${data.user} 在 mycard 首页发送给您的 ${data.type}
                </td>
            </tr>
            <tr style="background: #1FA2FF">
                <td style="padding: 0px">
                    <table style="border-spacing: 0px; border-collapse: collapse; width: 100%">
                        <tbody>
                        <tr>
                            <td style="padding: 0px; text-align: center;">
                                <img src="https://z3.ax1x.com/2021/09/30/4oKMVI.jpg" alt="George 的头像" width="30%" height="30%">
                            </td>
                        </tr>
                        <tr>
                            <td style="font-size: 38px; color: #FFFFFF; padding: 12px 22px 4px 22px; text-align: center;" colspan="3">
                                George chan's Card
                            </td>
                        </tr>
                        <tr>
                            <td style="font-size: 20px; color: #FFFFFF; padding: 0px 22px 18px 22px; text-align: center;" colspan="3">
                              让我给你讲讲他的传奇故事吧
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            <tr>
                <td style="background-color: #00BCD4; border-bottom-style: solid; border-bottom-color: #12D8FA; border-bottom-width: 4px;">
                    <table style="color: #333333; border-spacing: 0px; border-collapse: collapse; width: 100%; color: #fff">
                        <tbody>
                          <tr>
                            <td style="font-size: 18px; padding: 0px 0px 5px 0px;">
                              <p style="text-align: center">
                                <span style="font-weight:bold;">${data.user} </span>
                                <span>发送的主题：</span></p>
                                <p style="font-size: 16px; letter-spacing: 0.5px; text-indent: 16px; padding:0 20px; line-height: 30px; text-align: left;">
                                ${data.name}
                              </p>
                            </td>
                          </tr>
                          <tr>
                            <td style="font-size: 18px; padding: 0px 0px 5px 0px;">
                              <p style="text-align: center; margin-top: 0;">
                                <span style="font-weight:bold;">${data.user} </span>
                                <span>发送的内容：</span>
                              </p>
                                <p style="font-size: 16px;letter-spacing: 0.5px; text-indent: 16px; padding:0 20px; line-height: 30px; text-align: left;">
                                ${data.text},${data.link}
                              </p>
                            </td>
                          </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            </tbody>
        </table>
      </includetail>
</div>`

  return out;
}

const replyEmail = (data) => {
  let out=`<div style="width: 600px;margin: 0 auto;">
    <includetail>
      <table
        style="text-align: center; font-size: 16px; color: #333333; border-spacing: 0px; border-collapse: collapse; width: 580px; direction: ltr">
        <tbody>
          <tr>
            <td style="font-size: 14px; padding: 0px 0px 7px 0px; text-align: center;color: #1FA2FF">
              尊敬的 <span style="font-weight: bold;">${data.user}</span>，感谢您的${data.type}
            </td>
          </tr>
          <tr style="background-color: #1FA2FF">
            <td style="padding: 0px">
              <table style="border-spacing: 0px; border-collapse: collapse; width: 100%">
                <tbody>
                  <tr>
                    <td style="padding: 0px; text-align: center;">
                      <img src="https://z3.ax1x.com/2021/09/30/4oKMVI.jpg" alt="George 的头像" width="30%" height="30%">
                    </td>
                  </tr>
                  <tr>
                    <td style="font-size: 38px; color: #FFFFFF; padding: 12px 22px 4px 22px; text-align: center;"
                      colspan="3">
                      George Chan's Card
                    </td>
                  </tr>
                  <tr>
                    <td style="font-size: 20px; color: #FFFFFF; padding: 0px 22px 18px 22px; text-align: center;"
                      colspan="3">
                      让我给你讲讲他的传奇故事吧
                    </td>
                  </tr>
                </tbody>
              </table>
            </td>
          </tr>
          <tr>
            <td
              style="background-color: #00BCD4; border-bottom-style: solid; border-bottom-color: #12D8FA; border-bottom-width: 4px;">
              <table style="color: #333333; border-spacing: 0px; border-collapse: collapse; width: 100%; color: #fff">
                <tbody>
                  <tr>
                    <td style="font-size: 18px; padding: 0px 0px 5px 0px;">
                      <p style="text-align: center">
                        <span style="font-weight:bold">MyCard管理员</span>
                        <span>提示您：</span>
                      </p>
                      <p
                        style="font-size: 16px; letter-spacing: 0.5px; text-indent: 32px; padding:0 20px; line-height: 30px; text-align: left;">
                        本邮件由 MyCard 网站发送给您，
                        <span style="color: #1546a8; font-weight: bold;">如果非本人操作，请忽略即可。</span>
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td style="font-size: 18px; padding: 0px 0px 5px 0px;">
                      <p style="text-align: center; margin-top: 0;">
                        <span style="font-weight:bold">MyCard管理员</span>
                        <span>回复您：</span>
                      </p>
                      <p
                        style="font-size: 16px;letter-spacing: 0.5px; text-indent: 32px; padding:0 20px; line-height: 30px; text-align: left;">
                        ${data.out}
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td style="font-size: 16px; padding: 30px 20px; text-align: center">
                      如果您对我的网站感兴趣，请点击访问：
                      <p style="color: #0044CC;font-weight: bold">
                        <a href="http://www.cyc292.top/#/home" style="color: #0044CC;text-decoration: none">MyCard</a>
                      </p>
                      <p style="color: #0c3388;font-size: 14px; margin: 15px 0 0 0;">本网站仅是个人使用，并不带有商业用途</p>
                    </td>
                  </tr>
                </tbody>
              </table>
            </td>
          </tr>
        </tbody>
      </table>
    </includetail>
  </div>`
  return out;
}


export {
  recommendEmail,replyEmail,
}