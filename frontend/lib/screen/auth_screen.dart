import 'package:flutter/material.dart';
import 'package:plan_sync/const/color.dart';

class AuthScreen extends StatelessWidget {

  const AuthScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        // width: MediaQuery.sizeOf(context).width,
        decoration: BoxDecoration(
          color: SECONDARY_COLOR,
        ),
          child: Padding(
            padding: EdgeInsets.symmetric(horizontal: 16.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                Padding(
                  padding: EdgeInsets.all(16.0),
                  child: Image.asset("assets/img/logo-no-background.png", width: MediaQuery.sizeOf(context).width / 2,),
                ),
                IconButton(
                  onPressed: () {},
                  icon: Image.asset("assets/img/kakao_login_medium_wide.png"),
                ),
              ],
            ),
          )
      )

    );
  }
}