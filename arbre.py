import os

def generate_tree(directory, prefix=""):
    """
    Génère une représentation textuelle de l'arborescence d'un répertoire.
    
    :param directory: Chemin du répertoire de départ
    :param prefix: Préfixe pour l'indentation des sous-répertoires
    :return: Chaîne de l'arborescence
    """
    tree = []
    entries = sorted(os.listdir(directory))  # Tri pour une sortie cohérente
    entries_count = len(entries)

    for index, entry in enumerate(entries):
        path = os.path.join(directory, entry)
        connector = "├── " if index < entries_count - 1 else "└── "
        tree.append(f"{prefix}{connector}{entry}")

        if os.path.isdir(path):  # Si c'est un répertoire, le parcourir récursivement
            sub_prefix = "│   " if index < entries_count - 1 else "    "
            tree.extend(generate_tree(path, prefix + sub_prefix))

    return tree

def main():
    # Répertoire racine à analyser
    root_dir = os.path.abspath(".")  # Répertoire courant
    tree = generate_tree(root_dir)
    
    print(f"{os.path.basename(root_dir)}/")
    print("\n".join(tree))

if __name__ == "__main__":
    main()
