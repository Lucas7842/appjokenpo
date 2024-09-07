package br.com.aula.projeto05_jokenpo;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void selectPedra(View view){
      this.opcaoSelecionada("pedra");

    }
    public void selectPapel(View view){
        this.opcaoSelecionada("papel");
    }
    public void selectTesoura(View view){
        this.opcaoSelecionada("tesoura");

    }
    public void opcaoSelecionada(String opcaoSelicionada){
        ImageView imagemResultado = findViewById(R.id.imagePadrao);
        TextView textoResultado = findViewById(R.id.textResultado);

        // Lógica de Escolha da maquina (app):
        int numero = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes[numero];

        // Mudando a Imagem da escolha do app (conforme lófica do jogo):
        switch (opcaoApp){
            case "pedra":
                imagemResultado.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imagemResultado.setImageResource(R.drawable.papel);
                break;
            case"tesoura":
                imagemResultado.setImageResource(R.drawable.tesoura);
                break;
        }

        // Aplicar Lógica do jogo (Jo-ken-po):
        if(// App ganhando
                (opcaoApp == "pedra" && opcaoSelicionada == "tesoura") ||
                        (opcaoApp == "papel" && opcaoSelicionada == "pedra") ||
                        (opcaoApp == "tesoura" && opcaoSelicionada == "papel")
        ) {
            textoResultado.setText(R.string.appJogoGameOver);
        }else if (// jogador ganhando
                (opcaoSelicionada == "pedra" && opcaoApp == "tesoura") ||
                        (opcaoSelicionada == "papel" && opcaoApp == "pedra") ||
                        (opcaoSelicionada == "tesoura" && opcaoApp == "papel")
        ) {
            textoResultado.setText(R.string.appJogoWin);
        }else{// Empate
            textoResultado.setText(R.string.appJogoEmpate);

        }
    }
}