import os

def afficher_arborescence(chemin, prefix=""):
    """Affiche l'arborescence d'un dossier avec indentation et symboles."""
    fichiers_et_dossiers = sorted(os.listdir(chemin))
    
    for index, nom in enumerate(fichiers_et_dossiers):
        chemin_complet = os.path.join(chemin, nom)
        est_dernier = (index == len(fichiers_et_dossiers) - 1)
        prefix_suivant = prefix + ("│   " if not est_dernier else "    ")

        if os.path.isdir(chemin_complet):
            print(f"{prefix}├── {nom}/")
            afficher_arborescence(chemin_complet, prefix_suivant)
        else:
            print(f"{prefix}├── {nom}")

# Remplacez 'notification-service' par le chemin réel de votre dossier
chemin_du_dossier = "C:/Users/MBO_ALAIN_GERARD/Downloads/notification"
print(f"{chemin_du_dossier}/")
afficher_arborescence(chemin_du_dossier)
