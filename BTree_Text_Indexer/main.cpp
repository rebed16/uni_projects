#include "BTree.h"
#include <fstream>
#include <sstream>
#include <cctype>
#include <algorithm>

void processFile(const string &filename, BTree &tree) {
    ifstream file(filename);
    string line;
    int lineNumber = 0;

    while (getline(file, line)) {
        lineNumber++;
        stringstream ss(line);
        string word;
        int position = 0;

        while (ss >> word) {
            position++;
            // Normalizeaza cuvantul eliminand toate caracterele non-alfanumerice
            word.erase(remove_if(word.begin(), word.end(), [](unsigned char c) {
                return !isalnum(c);
            }), word.end());

            // Insereaza doar daca cuvantul nu este gol
            if (!word.empty()) {
                tree.insert(word, lineNumber, position);
            }
        }
    }
}

void exportToCSV(const string &outputFileName, BTree &tree) {
    ofstream outFile(outputFileName);
    if (!outFile.is_open()) {
        cerr << "Nu s-a putut deschide fisierul pentru scriere: " << outputFileName << endl;
        return;
    }

    outFile << "Cuvant,Locatii\n";
    tree.exportCSV(outFile);
    outFile.close();
}

int main() {
    int opt;
    BTree tree(3);

    cout<<"Alegeti cartea ale carei cuvinte doriti sa fie inserate in BTree, alaturi de pozitiile la care apare fiecare cuvant:";
    cout<<"\n1. And then there were none by Agatha Christie\n2. Pride and prejudice by Jane Austen\n3. The little prince by Antoine de Saint-Exupery\n";
    cin>>opt;
    string filename;
    if(opt == 1)
    {
        filename = "AndThenThereWereNone.txt";
    }
    else if(opt == 2)
    {
        filename = "PrideAndPrejudice.txt";
    }
    else if(opt == 3)
    {
        filename = "LittlePrince.txt";
    }
    else cout<<"Input incorect!";
    processFile(filename, tree);

    //cout << "Indexul creat:" << endl;
    //tree.traverse();

    string outputFileName = "index.csv";
    exportToCSV(outputFileName, tree);

    cout << "Indexul a fost exportat in fisierul " << outputFileName << endl;

    return 0;
}
