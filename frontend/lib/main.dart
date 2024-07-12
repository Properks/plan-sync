import 'package:flutter/material.dart';
import 'package:plan_sync/screen/auth_screen.dart';
import 'package:plan_sync/screen/home_screen.dart';
import 'package:kakao_flutter_sdk_common/kakao_flutter_sdk_common.dart';
import 'package:dotenv/dotenv.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();

  final env = DotEnv(includePlatformEnvironment: true)..load();
  KakaoSdk.init(
    nativeAppKey: env['NATIVE_APP_KEY'],
    javaScriptAppKey: env['JAVASCRIPT_APP_KEY']
  );
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: AuthScreen(),
    );
  }
}
