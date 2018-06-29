import com.mycompany.myapp.SecondaryConfigLoader
import com.mycompany.myapp.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    secondaryConfigLoader(SecondaryConfigLoader)
}
