# script_exemplo.py
import datetime

def main():
    print("🚀 Script iniciado")
    
    now = datetime.datetime.now()
    print(f"🕒 Data e hora atuais: {now}")

    # Exemplo de loop
    for i in range(5):
        print(f"🔹 Passo {i+1}")

    print("✅ Script finalizado com sucesso!")

if __name__ == "__main__":
    main()
